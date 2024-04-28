package br.com.Initialiizr.Informatica116.sistem.Security;

import br.com.Initialiizr.Informatica116.sistem.Models.Hardware;
import br.com.Initialiizr.Informatica116.sistem.Models.HardwareDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConvertJson {
    public <T> T convertJson(String data,Class<T> classT){
        Gson json = new Gson();
        T dats = json.fromJson(data,classT);
        return dats;
    }

}
