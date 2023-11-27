package com.turisup.resourcesservice.Repository;

import com.turisup.resourcesservice.Model.TouristPlace;
import com.turisup.resourcesservice.Model.UserApp;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends Neo4jRepository<UserApp, Long > {

    List<UserApp> findAll();

    Optional<UserApp> findById(Long userId);

    // Método para agregar un lugar turístico a la lista de favoritos del usuario
    @Query("MATCH (u:UserApp), (p:TouristPlace) " +
            "WHERE id(u) = $userId AND id(p) = $placeId " +
            "MERGE (u)-[r:FAVORITE]->(p) RETURN CASE WHEN r IS NOT NULL THEN true ELSE false END")
    boolean addFavoritePlace(@Param("userId") Long userId, @Param("placeId") Long placeId);

    // Método para obtener todos los lugares turísticos favoritos de un usuario
    @Query("MATCH (user:UserApp)-[:FAVORITE]->(place:TouristPlace) WHERE ID(user) = $userId RETURN ID(place) as placeId")
    List<Long> getFavoritePlaces(@Param("userId") Long userId);

    @Query("MATCH (user:UserApp)-[:FAVORITE]->(place:TouristPlace) WHERE ID(user) = $userId RETURN place")
    List<TouristPlace> getFavoritePlaces1(@Param("userId") Long userId);
    // Método para eliminar la relación "favorite" entre un usuario y un lugar turístico
    @Query("MATCH (u:UserApp)-[f:FAVORITE]->(p:TouristPlace) WHERE ID(u) = $userId AND ID(p) = $placeId DELETE f  RETURN f IS NOT NULL")
    boolean removeFavoritePlace(@Param("userId") Long userId, @Param("placeId") Long placeId);



    // Método para verificar si existe la relación "favorite" entre un usuario y un lugar turístico
    @Query("MATCH (u:UserApp)-[:FAVORITE]->(p:TouristPlace) WHERE ID(u) = $userId AND ID(p) = $placeId RETURN ID(u) IS NOT NULL AND ID(p) IS NOT NULL")
    boolean isFavoritePlace(@Param("userId") Long userId, @Param("placeId") Long placeId);


    /*
    @Transactional
    @Query("MATCH (u:User {userName: $userName}) SET u.email = $email, u.role = $role, u.imageUrl = $imageUrl RETURN u")
    User update(@Param("userName") String userName, @Param("email") String email, @Param("role") String role, @Param("imageUrl") String imageUrl);
*/

}
