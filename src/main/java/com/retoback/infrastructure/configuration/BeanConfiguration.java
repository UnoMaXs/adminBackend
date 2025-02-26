package com.retoback.infrastructure.configuration;


import com.retoback.domain.api.IPlatoServicePort;
import com.retoback.domain.api.IRestauranteServicePort;
import com.retoback.domain.api.IUsuarioServicePort;
import com.retoback.domain.spi.IPlatoPersistencePort;
import com.retoback.domain.spi.IRestaurantePersistencePort;
import com.retoback.domain.spi.IUsuarioPersistencePort;
import com.retoback.domain.usecase.PlatoUseCase;
import com.retoback.domain.usecase.RestauranteUseCase;
import com.retoback.domain.usecase.UsuarioUseCase;
import com.retoback.infrastructure.output.jpa.adapter.PlatoJpaAdapter;
import com.retoback.infrastructure.output.jpa.adapter.RestauranteJpaAdapter;
import com.retoback.infrastructure.output.jpa.adapter.UsuarioJpaAdapter;
import com.retoback.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.retoback.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.retoback.infrastructure.output.jpa.mapper.IUsuarioEntityMapper;
import com.retoback.infrastructure.output.jpa.repository.IPlatoRepository;
import com.retoback.infrastructure.output.jpa.repository.IRestauranteRepository;
import com.retoback.infrastructure.output.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;
    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort() {
        return new UsuarioJpaAdapter(usuarioRepository, usuarioEntityMapper);
    }

    @Bean
    public IUsuarioServicePort usuarioServicePort() {
        return new UsuarioUseCase(usuarioPersistencePort());
    }

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort() {
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort() {
        return new RestauranteUseCase(restaurantePersistencePort(), usuarioPersistencePort());
    }

    @Bean
    public IPlatoPersistencePort platoPersistencePort() {
        return new PlatoJpaAdapter(platoRepository, platoEntityMapper);
    }

    @Bean
    public IPlatoServicePort platoServicePort() {
        return new PlatoUseCase(platoPersistencePort());
    }

}

