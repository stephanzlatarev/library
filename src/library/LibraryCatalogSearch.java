package library;

import java.util.Collection;
import java.util.HashSet;

public class LibraryCatalogSearch implements CatalogSearch {

	private Catalog catalog;

	public LibraryCatalogSearch(Catalog catalog) {
		this.catalog = catalog;
	}

	public Collection<BookTitle> search(String keyword) {
		Collection<BookTitle> result = new HashSet<BookTitle>();

		for (BookTitle title: catalog.getTitles()) {
			if (title.getTitle().contains(keyword)) {
				result.add(title);
			}
		}

		return result;
	}

}