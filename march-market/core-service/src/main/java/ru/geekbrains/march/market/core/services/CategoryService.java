package ru.geekbrains.march.market.core.services;

import lombok.RequiredArgsConstructor;
import ru.geekbrains.march.market.core.entities.Category;
import ru.geekbrains.march.market.core.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
