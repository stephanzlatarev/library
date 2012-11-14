package library;

import java.util.ArrayList;
import java.util.Collection;

import tdl.SearchSession;
import tdl.TheDigitalLibrary;

public class TheDigitalLibraryCatalogBrowser {

	public TheDigitalLibraryCatalogBrowser() {
		TheDigitalLibrary.getLibrary().connect();
	}

	public Collection<BookTitle> search(String keyword) {
		SearchSession session = TheDigitalLibraryUtils.openSearch(keyword);

		try {
			Collection<BookTitle> result = new ArrayList<BookTitle>();
			Collection<String> headers = TheDigitalLibraryUtils.getFirstPageOfHeaders(session, keyword);
	
			while (!headers.isEmpty()) {
				for (String header: headers) {
					result.add(TheDigitalLibraryUtils.constructBookTitle(header));
				}
	
				headers = TheDigitalLibraryUtils.fetchNextPageOfHeaders(session);
			}

			return result;
		} finally {
			TheDigitalLibraryUtils.closeSearch(session);
		}
	}

}