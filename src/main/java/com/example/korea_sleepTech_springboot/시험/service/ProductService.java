package com.example.korea_sleepTech_springboot.시험.service;

import com.example.korea_sleepTech_springboot.시험.dto.request.ProductCreateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.request.ProductUpdateRequestDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ProductResponseDto;
import com.example.korea_sleepTech_springboot.시험.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.시험.entity.Product;
import com.example.korea_sleepTech_springboot.시험.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public ResponseDto createProduct(ProductCreateRequestDto createRequestDto){
        try{
            Product newProduct = new Product(
                    null,
                    createRequestDto.getName(),
                    createRequestDto.getDescription(),
                    createRequestDto.getPrice(),
                    null,null
            );

            Product savedProduct = productRepository.save(newProduct);

            ProductResponseDto responseDto = new ProductResponseDto(
                    savedProduct.getId(),
                    savedProduct.getName(),
                    savedProduct.getDescription(),
                    savedProduct.getPrice()
            );
            return ResponseDto.setSuccess("new Product Created",responseDto);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("No productsCreated"+e.getMessage());
        }
    }

    //read - all
    public List<ProductResponseDto> getAllProducts(){
        List<ProductResponseDto> responseDtos = null;
        try{
            List<Product> products = productRepository.findAll();

            responseDtos = products.stream()
                    .map(product -> new ProductResponseDto(
                            product.getId(),
                            product.getName(),
                            product.getDescription(),
                            product.getPrice()
                    )).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDtos;
    }

    //readbyId
    public ProductResponseDto getProductById(Long id){
        ProductResponseDto responseDto = null;

        try{
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("No product for Id : " +id ));

            responseDto = new ProductResponseDto(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    //update
    public ProductResponseDto updateProduct(Long id, ProductUpdateRequestDto dto){
        ProductResponseDto responseDto = null;
        try{
            Product product = productRepository.findById(id)
                    .orElseThrow(()->
                            new IllegalArgumentException(
                                    "Can't find the Product has"+id+"id"
                            ));
            product.setName(dto.getName());
            product.setDescription(dto.getDescription());
            product.setPrice(dto.getPrice());

            Product updatedProduct = productRepository.save(product);

            responseDto = new ProductResponseDto(
                    updatedProduct.getId(),
                    updatedProduct.getName(),
                    updatedProduct.getDescription(),
                    updatedProduct.getPrice()
            );


        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDto;
    }

    //delete
    public void deleteProduct(Long id) {
        try{
            Product product = productRepository.findById(id)
                    .orElseThrow(()->
                            new IllegalArgumentException(
                                    "No product for Id : " +id
                            ));
            productRepository.delete(product);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}



























