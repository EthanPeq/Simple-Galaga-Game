
public class Ship extends GameObject{
		int dx;
		
		Ship(){
			attribute = "ship";
		}
		
		public void update(){
			x+=dx;
		}
}
