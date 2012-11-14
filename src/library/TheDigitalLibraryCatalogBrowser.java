package library;

import java.util.ArrayList;
import java.util.Collection;

import tdl.SearchResults;
import tdl.SearchSession;
import tdl.TheDigitalLibrary;

public class TheDigitalLibraryCatalogBrowser {

	private TheDigitalLibrary library;
	private SearchSession session;

	public TheDigitalLibraryCatalogBrowser(TheDigitalLibrary library) {
		this.library = library;
		this.library.connect();
	}

	public Collection<BookTitle> search(String keyword) {
		try {
			Collection<BookTitle> result = new ArrayList<BookTitle>();
			Collection<String> headers = getFirstPageOfHeaders(keyword);
	
			while (!headers.isEmpty()) {
				for (String header: headers) {
					result.add(constructBookTitle(header));
				}
	
				headers = fetchNextPageOfHeaders();
			}

			return result;
		} finally {
			closeSearch();
		}
	}

	private final Collection<String> getFirstPageOfHeaders(String keyword) {
		session = library.openSearchSession();

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

	private final void closeSearch() {
		if (session != null) {
			library.closeSearchSession(session);
		}
	}

	private final BookTitle constructBookTitle(String bookHeading) {
		int index = bookHeading.lastIndexOf(" by ");
		String title = bookHeading.substring(0, index);
		String author = bookHeading.substring(index + 4);

		return new BookTitle(author, title);
	}

}