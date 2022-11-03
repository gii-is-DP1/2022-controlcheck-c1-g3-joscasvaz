package org.springframework.samples.petclinic.recoveryroom;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RecoveryRoomController {
	
	private RecoveryRoomService service;
	private static final String CREATE_OR_UPDATE_FORM =
			"/recoveryroom/createOrUpdateRecoveryRoomForm";
	
	@Autowired
	public RecoveryRoomController(RecoveryRoomService service) {
		this.service = service;
	}
	
	@Transactional
	@GetMapping(value = "/recoveryroom/create")
	public String initCreateOrUpdateForm(Map<String, Object> model) {
		
		List<String> typesName = service.getAllRecoveryRoomTypes()
				.stream()
				.map(RecoveryRoomType::getName)
				.toList();
		
		model.put("types", typesName);
		
		RecoveryRoom room = new RecoveryRoom();
		model.put("recovery_room", room);
		
		return CREATE_OR_UPDATE_FORM;
	}
	
	@Transactional
	@PostMapping(value = "/recoveryroom/create")
	public String createOrUpdate(RecoveryRoom room, BindingResult result) {
		
		if (result.hasErrors()) {
			
			return CREATE_OR_UPDATE_FORM;
			
		} else {
			
			try {
				service.save(room);
			} catch (DuplicatedRoomNameException e) {
				e.printStackTrace();
			}
			
			return "redirect:/";
		}
	}
    
}
