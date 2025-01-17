package ru.clevertec.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.responseDTO.ResponseHouseDTO;
import ru.clevertec.entity.type.PersonType;
import ru.clevertec.mapper.HouseMapper;
import ru.clevertec.repository.HouseHistoryRepository;
import ru.clevertec.service.HouseHistorySrevice;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class HouseHistorySreviceimpl implements HouseHistorySrevice {

    private HouseHistoryRepository houseRepository;
    private HouseMapper houseMapper;

    /**
     * Поиск всех домов, где проживал Person с параметром TENANT.
     * Поиск всех домов, которыми владел Person с параметром OWNER.
     *
     * @param personUuid uuid person.
     * @param type       TENANT или OWNER.
     * @return List<dto> домов.
     */
    @Override
    public List<ResponseHouseDTO> findAllHousesTenantOrOwner(UUID personUuid, PersonType type) {
        return houseRepository.findAllByHouseHistoriesPersonUuidAndHouseHistoriesType(personUuid, type).stream()
                .map(house -> houseMapper.toResponseHouseDTO(house))
                .toList();
    }
}
