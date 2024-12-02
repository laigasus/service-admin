package org.sesac.service_admin.service;

import lombok.RequiredArgsConstructor;
import org.sesac.service_admin.domain.product.Product;
import org.sesac.service_admin.model.ProductDTO;
import org.sesac.service_admin.repos.product.ProductRepository;
import org.sesac.service_admin.util.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductDTO> findAll(final String filter, final Pageable pageable) {
        Page<Product> page;
        if (filter != null) {
            Long longFilter = null;
            try {
                longFilter = Long.parseLong(filter);
            } catch (final NumberFormatException numberFormatException) {
                // keep null - no parseable input
            }
            page = productRepository.findAllById(longFilter, pageable);
        } else {
            page = productRepository.findAll(pageable);
        }
        return new PageImpl<>(page.getContent()
                .stream()
                .map(product -> productMapper.updateProductDTO(product, new ProductDTO()))
                .toList(),
                pageable, page.getTotalElements());
    }

    public ProductDTO get(final Long id) {
        return productRepository.findById(id)
                .map(product -> productMapper.updateProductDTO(product, new ProductDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final ProductDTO productDTO) {
        final Product product = new Product();
        productMapper.updateProduct(productDTO, product);
        return productRepository.save(product).getId();
    }

    public void update(final Long id, final ProductDTO productDTO) {
        final Product product = productRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        productMapper.updateProduct(productDTO, product);
        productRepository.save(product);
    }

    public void delete(final Long id) {
        productRepository.deleteById(id);
    }

}
