package library;

import java.util.ArrayList;
import java.util.Collection;

import tdl.SearchResults;
import tdl.SearchSession;
import tdl.TheDigitalLibrary;

public class TheDigitalLibraryCatalogSearch implements CatalogSearch {

	private boolean isConnected;
	private TheDigitalLibrary library;
	private SearchSessionPool pool;
	private SearchSession session;

	public TheDigitalLibraryCatalogSearch(TheDigitalLibrary library, SearchSessionPool pool) {
		this.library = library;
		this.pool = pool;
		this.isConnected = false;
	}

	public void connect() {
		if (!isConnected) {
			library.connect();
		}
	}

	public void disconnect() {
		if (session != null) {
			library.closeSearchSession(session);
		}
	}

	public Collection<BookTitle> search(String keyword) {
		Collection<BookTitle> result = new ArrayList<BookTitle>();
		Collection<String> headers = getFirstPageOfHeaders(keyword);

		while (!headers.isEmpty()) {
			for (String header: headers) {
				result.add(constructBookTitle(header));
			}

			headers = fetchNextPageOfHeaders();
		}

		return result;
	}

	private final Collection<String> getFirstPageOfHeaders(String keyword) {
		// make sure we are connected
		connect();

		session = pool.getSearchSession(library);

		SearchResults search = library.startSearch(session, keyword, 20);

		return readPageOfHeaders(search);
	}

	private final Collection<String> fetchNextPageOfHeaders() {
		SearchResults search = library.fetchNextPage(session);

		return readPageOfHeaders(search);
	}

	private final Collection<String> readPageOfHeaders(SearchResults search) {
		ArrayList<String> page = new ArrayList<String>();

		while (search.hasNextBookTitle()) {
			page.add(search.getBookTitle());
		}

		return page;
	}

	private final BookTitle constructBookTitle(String bookHeading) {
		int index = bookHeading.lastIndexOf(" by ");
		String title = bookHeading.substring(0, index);
		String author = bookHeading.substring(index + 4);

		return new BookTitle(author, title);
	}

}