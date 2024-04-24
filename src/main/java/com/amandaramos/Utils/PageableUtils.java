package com.amandaramos.Utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/*O método buildPageable cria e retorna um objeto Pageable, que é
usado para controlar a paginação dos resultados em consultas.
Ele recebe parâmetros como número da página, tamanho da página,
 campo de ordenação e direção de ordenação, e utiliza essas
 informações para construir o objeto Pageable.
 */
public class PageableUtils {

    public static Pageable buildPageable(int page, int size, String sortField, String sortOrder) {
        Sort.Direction direction = Sort.Direction.fromString(sortOrder);
        Sort sort = Sort.by(direction, sortField);
        return PageRequest.of(page, size, sort);
    }
}