 
@Entity
 public class Projects{
   @PrimaryKey
   private int id;
   private String bookName;   //should change
   private int page;
   private String textMessage;
   private Duration time;
   private String imageFile;//get absolute path
   private String audioFile;//
   
   public Projects(int id){
   	this.id = id;
   }
   public String getbookName(){
   	return bookName;
   }
   public int getPage(){
   	return page;
   }
   public String getTextMessage(){
   	return textMessage;
   }
   public Duration getTime(){
   	return time;
   }
   public String getImageFile(){
   	return imageFile;
   }
   public String getAudioFile(){
   	return audioFile;
   }
 }

@Dao
public interface ProjectsDao {
	@Insert(onConflict = IGNORE)
	void insert(Projects project);
	@Delete
	void delete(Projects project);

	@Query("UPDATE Projects SET bookName = :name WHERE id == :userIds")
	public  updateBookName(String name, int userIds);  

	@Query("UPDATE Projects SET page = :page WHERE id LIKE :userIds")
	public  updatePage(int page, int userIds);  

	@Query("UPDATE Projects SET textMessage = :textMessage WHERE id LIKE :userIds")
	public  updateTextMessage(String textMessage, int userIds);  

	@Query("UPDATE Projects SET time = :time WHERE id LIKE :userIds")
	public  updateTime(Duration time, int userIds);  

	@Query("UPDATE Projects SET imageFile = :imageFile WHERE id LIKE :userIds")
	public  updateTime(String imageFile, int userIds);  

	@Query("UPDATE Projects SET audioFile = :audioFile WHERE id LIKE :userIds")
	public  updateTime(String audioFile, int userIds); 

	@Query("Select * FROM Projects WHERE id LIKE :userIds")
	public Projects findProjectById(int userId);

	@Query("Select * FROM Projects")
	public List<Projects> getAllProjects();


}

@Database(entities = {Projects.class})
 public abstract class AppDatabase extends RoomDatabase {
   public abstract ProjectsDao projectsDao();

   public static AppDatabase getInMememoryDatabase(Context context){
   	if(INSTANCE == null){
   		INSTANCE = Room.InMemoryDatabaseBuilder(context.getApplicationContext(),
   							AppDatabase.class,
   							"database-filename")
   							.build();
   	}
   	return INSTANCE;
   }

   public static void destroyInstance() {INSTANCE = null;}
 }

 //
 private void fetchData(int id) {
 	DB = AppDatabase.getInMemoryDatabase(getApplicationContext());

 	StringBuilder sb = new StringBuilder();
 	Projects project = DB.projectsDao().findProjectById(id);
 }