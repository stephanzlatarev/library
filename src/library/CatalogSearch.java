package library;

import java.util.Collection;

public interface CatalogSearch {

	public Collection<BookTitle> search(String keyword);

}