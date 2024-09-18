package pe.edu.upeu.calcfx.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcServiceImpl implements CalcServiceI {
    private List<CalcTO> resultados = new ArrayList<>();

    @Override
    public void guardarResultados(CalcTO to) {
        resultados.add(to);
    }

    @Override
    public List<CalcTO> obtenerResultados() {
        return resultados;
    }

    @Override
    public void eliminarResultados(int index) {
        if (index >= 0 && index < resultados.size()) {
            resultados.remove(index);
        }
    }

    @Override
    public void actualizarResultados(CalcTO to, int index) {
        if (index >= 0 && index < resultados.size()) {
            resultados.set(index, to);
        }
    }
}