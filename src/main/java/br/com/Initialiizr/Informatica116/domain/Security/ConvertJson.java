package br.com.Initialiizr.Informatica116.domain.Security;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class ConvertJson {
    public <T> T convertJson(String data,Class<T> classT){
        Gson json = new Gson();
        T dats = json.fromJson(data,classT);
        return dats;
    }

}
