package rs.ftn.poslovna.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ftn.poslovna.domain.Banka;
import rs.ftn.poslovna.repository.BankaRepository;
import rs.ftn.poslovna.service.BankaService;

@Service
public class BankaServiceImpl implements BankaService{
	
	@Autowired
	private BankaRepository bankaRepository;

	@Override
	public Banka addBanka(Banka banka) {
		return bankaRepository.save(banka);
	}

}
