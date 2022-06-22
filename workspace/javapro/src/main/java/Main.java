import code.Setting;
import code.Start;
import javapro.JsonFileEdit;

public class Main extends Setting{
    public static void main(String[] args) {
    	// 테스트 계정용
		setName("test");
		setId("1");
		setPw("1");
		setBrand("신림사거리");
		setLocation("관악구");
		emp = false;
		setEmpsal(0);
		
		Setting s = new Setting();
		
		// Frame 기본 세팅 정보
		width = 1600;
		height = 1200;
		font = JsonFileEdit.get("font");
		setFonts(font);
		
    	new Start();
    } 
}