package library;

import java.util.LinkedList;

import tdl.SearchSession;
import tdl.TheDigitalLibrary;

public class SearchSessionPool {

	private int limit;
	private LinkedList<SearchSession> pool;

	public SearchSessionPool(int limit) {
		this.limit = limit;
		this.pool = new LinkedList<SearchSession>();
	}

	public SearchSession getSearchSession(TheDigitalLibrary library) {
		SearchSession session = pool.poll();

		if (session != null) {
			return session;
		} else {
			return library.openSearchSession();
		}
	}

	public void returnToPool(SearchSession session) {
		if (pool.size() < limit) {
			pool.add(session);
		}
	}

}