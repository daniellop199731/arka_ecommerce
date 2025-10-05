package com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bancolombia.arka_ecommerce.domain.model.Comment;
import com.bancolombia.arka_ecommerce.infrastructure.adapter.out.persistence.entity.ProductEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {

    @Query("{_id:ObjectId( '68c1e1fc63db736abc62da57' )}, {_id:0,'comments':1}")
    Flux<Comment> findCommentsByProductId(String id);

    @Query("[{ $unwind: '$comments' }, { $sort: { 'comments.likes': -1 } }, { $limit: 1 }, {$project: {_id: 0,productId: '$id',comment: '$comments.text'}}]")
    Mono<String> findInOrderBestComment();

    //EXPLICACION DEL QUERY
    /*
        db.products.aggregate ([
            { $unwind: "$comments" },                 // Descomponer el array de comentarios
            { $sort: { "comments.likes": -1 } },      // Ordenar por likes descendente
            { $limit: 1 },                            // Quedarse solo con el primero
            {
                $project: {
                _id: 0,
                productId: "$id",
                productName: "$name",
                comment: "$comments.text",
                likes: "$comments.likes"
                }
            }
        ])
     */
}
