package br.com.Initialiizr.Informatica116.sistem.Service;

import br.com.Initialiizr.Informatica116.sistem.DTO.AUTH_DAO.UserActiveDTO;
import br.com.Initialiizr.Informatica116.sistem.Models.AUTH_USER.UserActive;
import br.com.Initialiizr.Informatica116.sistem.repository.UserActiveRepository;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.Enumeration;

@Service
public class UserActivityService {
    private static final int INACTIVITY_LIMIT_MINUTES = 5; // Defina o limite de inatividade em minutos

    private final UserActiveRepository userActivityRepository;

    public UserActivityService(UserActiveRepository userActivityRepository) {
        this.userActivityRepository = userActivityRepository;
    }

    public void updateLastActivity(String username) throws SocketException {
        LocalDateTime now = LocalDateTime.now();

        // Tenta encontrar a atividade do usuário
        UserActive userActivity = userActivityRepository.findByUserName(username);
        if (userActivity != null) {
            // Atualiza a data da última atividade
            userActivity.setTimestamp(now);
            userActivity.setOnlineUsuario(true);
            userActivityRepository.save(userActivity);
        } else {
            userActivity = new UserActive(username, now,false,getWife());
            userActivityRepository.save(userActivity);
        }
     }

    public UserActiveDTO isUserOnline(String username) {
           UserActive userActivity = userActivityRepository.findByUserName(username);

           LocalDateTime lastActivity = userActivityRepository.getLastActivity(username);
           if(lastActivity!=null){
               LocalDateTime salvoActive = userActivity.getTimestamp();
               LocalDateTime inactive = LocalDateTime.now().minusMinutes(INACTIVITY_LIMIT_MINUTES);
               return new UserActiveDTO( salvoActive.isAfter(inactive),userActivity.getTimestamp());
           }
           return new UserActiveDTO(false,LocalDateTime.now());

    }
    private String getWife() throws SocketException {
        Enumeration<NetworkInterface> networkInterface = NetworkInterface.getNetworkInterfaces();
        while (networkInterface.hasMoreElements()){
            NetworkInterface net = networkInterface.nextElement();
            if(net.isUp() && !net.isLoopback()&&!net.isVirtual()){
                    Enumeration<InetAddress> inetAddress = net.getInetAddresses();
                    while (inetAddress.hasMoreElements()) {
                        InetAddress inet = inetAddress.nextElement();
                        if (inet instanceof java.net.Inet4Address) {
                            return inet.getHostAddress();
                        }
                    }

            }
        }
        return "Ip desconhecido";
    }
}
//lastActivity != null && lastActivity.isAfter(LocalDateTime.now().minusMinutes(5));
