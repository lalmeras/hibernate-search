package org.hibernate.search.test.embedded.fieldoncollection;

import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.Search;
import org.hibernate.search.test.SearchTestCase;
import org.hibernate.search.test.embedded.depth.Person;

public class LazyIndirectCollectionBridgeReindexTest extends SearchTestCase {

	public void testLazyIndirectCollectionBridgeReindex() throws InterruptedException {
		prepareEntities();
		verifyMatchExistsWithName( "name", "name" );
		
		Session session = openSession();
		FullTextSession fullTextSession = Search.getFullTextSession( session );
		MassIndexer massIndexer = fullTextSession.createIndexer(Root.class);
		massIndexer.startAndWait();
		verifyMatchExistsWithName( "name", "name" );
	}

	private void verifyMatchExistsWithName(String fieldName, String fieldValue) {
		FullTextSession fullTextSession = Search.getFullTextSession( openSession() );
		try {
			Transaction transaction = fullTextSession.beginTransaction();
			Query q = new TermQuery( new Term( "name", "name" ) );
			FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery( q );
			int resultSize = fullTextQuery.getResultSize();
			assertEquals( 1, resultSize );
			
			@SuppressWarnings("unchecked")
			List<Person> list = fullTextQuery.list();
			assertEquals( 1, list.size() );
			transaction.commit();
		}
		finally {
			fullTextSession.close();
		}
	}

	private void prepareEntities() {
		Session session = openSession();
		Transaction transaction = session.beginTransaction();
		
		CollectionItem bridgedEntity = new CollectionItem();
		session.save(bridgedEntity);
		
		Leaf leaf = new Leaf();
		leaf.getCollectionItems().add(bridgedEntity);
		session.save(leaf);
		
		Root root = new Root();
		root.setName("name");
		root.setLeaf(leaf);
		session.save(root);
		
		transaction.commit();
		session.close();
	}

	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class<?>[] { CollectionItem.class, Leaf.class, Root.class };
	}

}
