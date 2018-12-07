package uet.oop.bomberman.level;

import java.io.InputStream;
import java.util.Scanner;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Doll;
import uet.oop.bomberman.entities.character.enemy.Minvo;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được
	 * từ ma trận bản đồ trong tệp cấu hình
	 */
	private static char[][] _map;

	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}

	@Override
	public void loadLevel(int level) {
		// TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
		// TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
		String path = "/levels/Level" + level+ ".txt";
		InputStream file = getClass().getResourceAsStream(path);
		try(Scanner sc=new Scanner(file)){
			String a=sc.nextLine();
			String [] b=a.split(" ");
			_level=Integer.parseInt(b[0]);
			_height=Integer.parseInt(b[1]);
			_width=Integer.parseInt(b[2]);

			_map=new char[_height][_width];
			for(int i=0;i<_height;i++){
				String line=sc.nextLine();
				for(int j=0;j<_width;j++){
					_map[i][j]=line.charAt(j);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void createEntities() {
		// TODO: tạo các Entity của màn chơi
		// TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

		// TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
		// TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
		for (int x = 0; x < getWidth(); x++) {
			for (int y = 0; y < getHeight(); y++) {
				switch (_map[y][x]){
					// thêm Wall
					case '#':
						_board.addEntity(x + y * _width, new Wall(x, y, Sprite.wall));
						break;

					// thêm Bomber
					case 'p':
						_board.addCharacter( new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board) );
						Screen.setOffset(0, 0);
						_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;

					// thêm Balloon
					case '1':
						_board.addCharacter( new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;

					// thêm Oneal
					case '2':
						_board.addCharacter( new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
						_board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass));
						break;
					// thêm Doll
					case '3':
						_board.addCharacter(new Doll(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y) +Game.TILES_SIZE,_board));
						_board.addEntity(x + y *_width,new Grass(x,y,Sprite.grass));
						break;
					// thêm Minvo
					case '4':
						_board.addCharacter(new Minvo(Coordinates.tileToPixel(x),Coordinates.tileToPixel(y) +Game.TILES_SIZE,_board));
						_board.addEntity(x + y *_width,new Grass(x,y,Sprite.grass));
					// thêm Brick
					case '*':
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
										new Grass(x, y, Sprite.grass),
										new Brick(x, y, Sprite.brick)
								)
						);
						break;

					// thêm BombItem kèm Brick che phủ ở trên
					case 'b':
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
										new Grass(x ,y, Sprite.grass),
										new BombItem(x, y, Sprite.powerup_bombs),
										new Brick(x, y, Sprite.brick)
								)
						);
						break;

					// thêm Flame Item kèm Brick che phủ ở trên
					case 'f':
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
										new Grass(x ,y, Sprite.grass),
										new FlameItem(x, y, Sprite.powerup_flames),
										new Brick(x, y, Sprite.brick)
								)
						);
						break;

					// thêm Speed Item kèm Brick che phủ ở trên
					case 's':
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
										new Grass(x ,y, Sprite.grass),
										new SpeedItem(x, y, Sprite.powerup_speed),
										new Brick(x, y, Sprite.brick)
								)
						);
						break;

					// them Portal
					case 'x':
						_board.addEntity(x + y * _width,
								new LayeredEntity(x, y,
										new Grass(x ,y, Sprite.grass),
										new Portal(x, y, Sprite.portal),
										new Brick(x, y, Sprite.brick)
								)
						);
						break;

					default:
						_board.addEntity(x + y * _width,new Grass(x, y, Sprite.grass));
						break;

				}
			}
		}
	}
}

