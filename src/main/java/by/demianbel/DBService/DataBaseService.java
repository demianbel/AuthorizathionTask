package by.demianbel.DBService;

import by.demianbel.DBService.domain.UserDataSet;
import by.demianbel.DBService.repository.ItemRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataBaseService {
    @Autowired
    private ItemRepository itemRepository;

    public long createUser(@NonNull UserDataSet user){
        return itemRepository.save(user).getId();
    }

    public String updateStatus(@NonNull UserDataSet user,@NonNull String status){
        user.setOnlineStatus(status);
        return itemRepository.save(user).getOnlineStatus();
    }

    public UserDataSet getUserById(@NonNull Long id){
        return itemRepository.findOne(id);
    }

}
