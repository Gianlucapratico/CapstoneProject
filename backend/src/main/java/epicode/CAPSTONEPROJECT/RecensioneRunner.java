package epicode.CAPSTONEPROJECT;

//@Order(4)
//@Component
//public class RecensioneRunner implements CommandLineRunner {
//
//	@Autowired
//	RecensioneRepository recensioneRepository;
//	@Autowired
//	PrenotazioneRepository prenotazionerepo;
//
//	@Override
//	public void run(String... args) {
//		Faker faker = new Faker(new Locale("it"));
//		Random random = new Random();
//		if (recensioneRepository.count() == 0) {
//
//			for (int i = 0; i < 20; i++) {
//				try {
//					String commento = faker.lorem().sentence();
//
//					int valutazione = faker.random().nextInt(1, 10);
//
//					Recensione recensione = new Recensione(commento, valutazione, null);
//					recensioneRepository.save(recensione);
//
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//			}
//		}
//	}
//
//}
