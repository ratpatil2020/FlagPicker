package com.apple.entity.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apple.entity.FlagPicker;

@Repository
public interface FlagPickerRepository extends JpaRepository<FlagPicker, Long>{
	
	public Optional<FlagPicker> findByType(String type);	
	public Optional<List<FlagPicker>> findAllByType(String type);
}