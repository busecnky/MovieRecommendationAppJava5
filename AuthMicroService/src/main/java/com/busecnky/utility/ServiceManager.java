package com.busecnky.utility;

import com.busecnky.repository.entity.BaseEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Getter
public class ServiceManager<T extends BaseEntity,ID> implements IService<T, ID> {

    private final JpaRepository<T, ID> repository;


    @Override
    public T save(T t) {
        t.setCreatedate(System.currentTimeMillis());
        t.setUpdatedate(System.currentTimeMillis());
        return repository.save(t);
    }

    @Override
    public Iterable<T> saveAll(Iterable<T> t) {
        t.forEach(x->{
            x.setCreatedate(System.currentTimeMillis());
            x.setUpdatedate(System.currentTimeMillis());

        });

        return repository.saveAll(t);
    }
    /**
     * Update için özellikle bir update komutu yoktur. Eper Entity içinde ID bilgisi
     * bulunuyorsa, ilgili id kaydı için entity içindeki yeni değerler değiştirilir.
     */

    @Override
    public T update(T t) {

        t.setUpdatedate(System.currentTimeMillis());

        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }
}
