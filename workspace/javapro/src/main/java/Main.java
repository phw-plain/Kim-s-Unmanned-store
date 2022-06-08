import code.Setting;
import code.Start;
import javapro.JsonFileEdit;

public class Main extends Setting{
    public static void main(String[] args) {
    	// 테스트 계정용
		name = "test";
		id = "1";
		pw = "1";
		brand = "신림사거리";
		location = "관악구";
		emp = false;
		empsal = 0;
		
		Setting s = new Setting();
		
		// Frame 기본 세팅 정보
		width = 1024;
		height = 786;
		font = JsonFileEdit.get("font");
		setFonts(font);
		
    	new Start();
    } 
}