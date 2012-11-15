package library;

import java.util.Collection;

public class TheDigitalLibraryCatalogSearch implements CatalogSearch {

	private TheDigitalLibraryCatalogBrowser library;

	public TheDigitalLibraryCatalogSearch(TheDigitalLibraryCatalogBrowser library) {
		this.library = library;
	}

	public Collection<BookTitle> search(String keyword) {
		library.connect();
		try {
			return library.search(keyword);
		} finally {
			library.disconnect();
		}
	}

}