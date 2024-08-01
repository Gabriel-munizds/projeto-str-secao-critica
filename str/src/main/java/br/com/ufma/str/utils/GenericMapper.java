package br.com.ufma.str.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GenericMapper implements IGenericMapper{
    @Autowired
    ModelMapper modelMapper;
    @Override
    public <T> T toObject(Object obj, Class<T> clazz) {
        return Objects.isNull(obj) ? null : this.modelMapper.map(obj, clazz);
    }

    @Override
    public <T> List<T> toList(List<?> list, Class<T> clazz) {
        return !Objects.isNull(list) && !list.isEmpty() ? list.stream().map(
                (obj) -> this.modelMapper.map(obj, clazz))
                .collect(Collectors.toList()) : Collections.emptyList();
    }
}
