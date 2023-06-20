package uz.es.company.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.es.company.dto.request.CompanyRequestDto;
import uz.es.company.entity.company.CompanyEntity;

import uz.es.company.exception.DataNotFoundException;
import uz.es.company.repository.CompanyRepository;

import java.security.Principal;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;


    public CompanyEntity save(CompanyRequestDto companyRequestDto) {
        CompanyEntity company = modelMapper.map(companyRequestDto, CompanyEntity.class);
        return companyRepository.save(company);
    }

    public CompanyEntity getById(UUID companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new DataNotFoundException("This Company not found"));
    }

    public CompanyEntity update(CompanyRequestDto companyRequestDto, Principal principal) {
        CompanyEntity company = companyRepository.findCompanyEntityByPhoneNumber(principal.getName()).orElseThrow(
                () -> new DataNotFoundException("Company not found"));
        modelMapper.map(companyRequestDto, company);
        return companyRepository.save(company);
    }

    public void delete(UUID companyId) {
        companyRepository.deleteById(companyId);
    }
}


