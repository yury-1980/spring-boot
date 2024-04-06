package ru.clevertec.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.dto.responseDTO.ResponsePersonDTO;
import ru.clevertec.entity.type.PersonType;
import ru.clevertec.mapper.PersonMapper;
import ru.clevertec.repository.HouseHistoryPersonRepository;
import ru.clevertec.service.HouseHistoryPersonSrevice;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class HouseHistoryPersonSreviceimpl implements HouseHistoryPersonSrevice {

    private HouseHistoryPersonRepository tenantRepository;
    private PersonMapper personMapper;

    /**
     * Поиск всех Persons владеющих или проживающих в этом доме.
     *
     * @param houseUuid uuid дома.
     * @param type      OWNER или TENANT.
     * @return Список PersonDTO.
     */
    @Override
    public List<ResponsePersonDTO> findAllPersonsTenantsOrPersonsOwnersInHouse(UUID houseUuid, PersonType type) {
        return tenantRepository.findAllByPersonHistoriesHouseUuidAndPersonHistoriesType(houseUuid, type).stream()
                .map(person -> personMapper.toResponsePersonDto(person))
                .toList();
    }
}