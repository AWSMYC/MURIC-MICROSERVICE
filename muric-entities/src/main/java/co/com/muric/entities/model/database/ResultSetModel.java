package co.com.muric.entities.model.database;

import co.com.muric.entities.model.excel.AtributoCreditoDeuda;
import co.com.muric.entities.model.excel.InformacionCredito;
import co.com.muric.entities.model.excel.MovimientoCartera;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ResultSetModel {
    List<InformacionCredito> informacionCreditoList = new ArrayList<>();
    List<AtributoCreditoDeuda> atributoCreditoDeudaList = new ArrayList<>();
    List<MovimientoCartera> movimientoCarteraList = new ArrayList<>();
}