package epicode.CAPSTONEPROJECT.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import epicode.CAPSTONEPROJECT.entities.Role;
import epicode.CAPSTONEPROJECT.entities.User;
import epicode.CAPSTONEPROJECT.exceptions.BadRequestException;
import epicode.CAPSTONEPROJECT.exceptions.NotFoundException;
import epicode.CAPSTONEPROJECT.payloads.UserCreatePayload;
import epicode.CAPSTONEPROJECT.repositories.RoleRepository;
import epicode.CAPSTONEPROJECT.repositories.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepo;

	@Autowired
	private RoleRepository roleRepo;

	public User create(UserCreatePayload u) {
		usersRepo.findByEmail(u.getEmail()).ifPresent(user -> {
			throw new BadRequestException("Email" + user.getEmail() + "already in use!");
		});
		User newUser = new User(u.getNome(), u.getCognome(), u.getUsername(), u.getEmail(), u.getPassword());

		Role roleDefault = roleRepo.findByNome("USER")
				.orElseThrow(() -> new NotFoundException("Ruolo USER non esiste!!"));
		newUser.getRoles().add(roleDefault);

		return usersRepo.save(newUser);
	}

	public Page<User> find(int page, int size, String sortBy) {
		if (size < 0)
			size = 10;
		if (size > 100)
			size = 100;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

		return usersRepo.findAll(pageable);
	}

	public User findById(UUID id) throws NotFoundException {
		return usersRepo.findById(id).orElseThrow(() -> new NotFoundException("Utente con Id:" + id + "non trovato!!"));

	}

	public User findByEmail(String email) throws NotFoundException {
		return usersRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email:" + email + "non trovato!!"));
	}

	public User findByUsername(String username) throws NotFoundException {
		return usersRepo.findByUsername(username)
				.orElseThrow(() -> new NotFoundException("Utente:" + username + "non trovato!!"));
	}

	public User findByIdAndUpdate(UUID id, UserCreatePayload u) throws NotFoundException {
		User found = this.findById(id);

		found.setId(id);
		found.setNome(u.getNome());
		found.setCognome(u.getCognome());
		found.setUsername(u.getUsername());
		found.setEmail(u.getEmail());
		found.setPassword(u.getPassword());

		return usersRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		User found = this.findById(id);
		usersRepo.delete(found);
	}
}