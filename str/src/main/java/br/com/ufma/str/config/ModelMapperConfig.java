package br.com.ufma.str.config;

import br.com.ufma.str.dto.TransacaoDtoIn;
import br.com.ufma.str.dto.TransacaoDtoOut;
import br.com.ufma.str.model.TipoTransacao;
import br.com.ufma.str.model.Transacao;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        Converter<Long, TipoTransacao> tipoTransacaoConverter = context -> context.getSource() == null ? null : new TipoTransacao(context.getSource());

        modelMapper.createTypeMap(TransacaoDtoIn.class, Transacao.class)
                .addMappings(mapping -> mapping.using(tipoTransacaoConverter)
                        .map(TransacaoDtoIn::getTipoTransacao, Transacao::setTipoTransacao));

        modelMapper.createTypeMap(Transacao.class, TransacaoDtoOut.class)
                .addMapping(source -> source.getTipoTransacao().getDescricao(), TransacaoDtoOut::setTipoTransacao);

        return modelMapper;
    }

}
