
package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.UI;


public class Map {
    
    UI ui;
    public Tile[] tile;
    public char mapArray[][]; // each tile is assigned an int. this is the array for the maps
    
    public int tileLength = 251;
    public int tileWidth = 235;
    public int halfTileHeight = 70;
    
    int horizontalLimit = 10;
    int verticalLimit = 15;
    
    public int returnCol; // these are for when column and row are calculated and needs to be returned to UI
    public int returnRow;
    
    public Map(UI ui) {
        
        this.ui = ui;
        tile = new Tile[39]; // 39 different tiles
        mapArray = new char[horizontalLimit][verticalLimit];
        getTileImage();
        loadMap("/maps/map.txt");
        
        
    }
    
    public void getTileImage() {
        
        /*
        
        Tile no. Index
        
        0 = normal grass            1
        1 = grass with building     2
        2 = sand                    3
        3 = sand with building      4
        4 = snow                    5
        5 = snow with building      6
        
        SPRING ROAD TILES
        6 = horizontal road (1)     7
        7 = vertical road (2)       8
        8 = cross road (3)          9
        9 = left t junction (4)     a
        10 = up t junction (5)      b
        11 = right t junction (6)   c
        12 = down t junction (7)    d
        13 = down right turn (8)    e
        14 = left up turn (9)       f
        15 = up right turn (10)     g
        16 = left down turn (11)    h
        
        DESERT
        17 = horizontal road (1)     i
        18 = vertical road (2)       j
        19 = cross road (3)          k
        20 = left t junction (4)     l
        21 = up t junction (5)      m
        22 = right t junction (6)   n
        23 = down t junction (7)    o
        24 = down right turn (8)    p
        25 = left up turn (9)       q
        26 = up right turn (10)     r
        27 = left down turn (11)    s
        
        SNOW
        28 = horizontal road (1)     t
        29 = vertical road (2)       u
        30 = cross road (3)          v
        31 = left t junction (4)     w
        32 = up t junction (5)      x
        33 = right t junction (6)   y
        34 = down t junction (7)    z
        35 = down right turn (8)    A
        36 = left up turn (9)       B
        37 = up right turn (10)     C
        38 = left down turn (11)    D
        
        */
        try {
            
            //landscape tiles
            
            File file = new File("res/landscape/grass.png");
            FileInputStream fis = new FileInputStream(file);
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(fis);
            
            file = new File("res/landscape/grassBuilding.png");
            fis = new FileInputStream(file);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(fis);
            
            file = new File("res/landscape/sand.png");
            fis = new FileInputStream(file);
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(fis);
            
            file = new File("res/landscape/sandBuilding.png");
            fis = new FileInputStream(file);
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(fis);
            
            file = new File("res/landscape/snow.png");
            fis = new FileInputStream(file);
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(fis);
            
            file = new File("res/landscape/snowBuilding.png");
            fis = new FileInputStream(file);
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(fis);
            
            
            // SPRING ROAD TILES
            
            
            file = new File("res/road/spring/road_spring(1).png");
            fis = new FileInputStream(file);
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(2).png");
            fis = new FileInputStream(file);
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(3).png");
            fis = new FileInputStream(file);
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(4).png");
            fis = new FileInputStream(file);
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(5).png");
            fis = new FileInputStream(file);
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(6).png");
            fis = new FileInputStream(file);
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(7).png");
            fis = new FileInputStream(file);
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(8).png");
            fis = new FileInputStream(file);
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(9).png");
            fis = new FileInputStream(file);
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(10).png");
            fis = new FileInputStream(file);
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(fis);
            
            file = new File("res/road/spring/road_spring(11).png");
            fis = new FileInputStream(file);
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(fis);
            
            
            // SAND ROAD TILES
            
            file = new File("res/road/desert/road_desert(1).png");
            fis = new FileInputStream(file);
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(2).png");
            fis = new FileInputStream(file);
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(3).png");
            fis = new FileInputStream(file);
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(4).png");
            fis = new FileInputStream(file);
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(5).png");
            fis = new FileInputStream(file);
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(6).png");
            fis = new FileInputStream(file);
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(7).png");
            fis = new FileInputStream(file);
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(8).png");
            fis = new FileInputStream(file);
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(9).png");
            fis = new FileInputStream(file);
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(10).png");
            fis = new FileInputStream(file);
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(fis);
            
            file = new File("res/road/desert/road_desert(11).png");
            fis = new FileInputStream(file);
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(fis);
            
            
            
            
            // SNOW ROAD TILES
            
            file = new File("res/road/winter/road_winter(1).png");
            fis = new FileInputStream(file);
            tile[28] = new Tile();
            tile[28].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(2).png");
            fis = new FileInputStream(file);
            tile[29] = new Tile();
            tile[29].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(3).png");
            fis = new FileInputStream(file);
            tile[30] = new Tile();
            tile[30].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(4).png");
            fis = new FileInputStream(file);
            tile[31] = new Tile();
            tile[31].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(5).png");
            fis = new FileInputStream(file);
            tile[32] = new Tile();
            tile[32].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(6).png");
            fis = new FileInputStream(file);
            tile[33] = new Tile();
            tile[33].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(7).png");
            fis = new FileInputStream(file);
            tile[34] = new Tile();
            tile[34].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(8).png");
            fis = new FileInputStream(file);
            tile[35] = new Tile();
            tile[35].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(9).png");
            fis = new FileInputStream(file);
            tile[36] = new Tile();
            tile[36].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(10).png");
            fis = new FileInputStream(file);
            tile[37] = new Tile();
            tile[37].image = ImageIO.read(fis);
            
            file = new File("res/road/winter/road_winter(11).png");
            fis = new FileInputStream(file);
            tile[38] = new Tile();
            tile[38].image = ImageIO.read(fis);
            
            
            
            
            System.out.println("tiles loaded succesfully");
            
        } catch (IOException e) {
            System.out.println("error loading tiles");
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath) {
        
        
        try {
            
            
            InputStream is = getClass().getResourceAsStream(filePath); // import the text file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // reads the text file
            
            for(int y=0; y<verticalLimit; y++) {
                String line = br.readLine(); // reads whole single line from txt file
                line = line.replaceAll(" ", ""); // removes the spaces in between
                char charArray[] = line.toCharArray();
                
                
                
                for(int x=0; x<horizontalLimit; x++) {
                    mapArray[x][y] = charArray[x];
                }
                
                
            }
            br.close();
            System.out.println("map loaded");
            
            
        } catch (Exception e) {
            System.out.println("error loading map");
            System.out.println(e);
        }
        
    }
    
    public Tile getTileCode(char code) {
        switch(code) {
            case('0'):
                return tile[0];
            case('1'):
                return tile[1];
            case('2'):
                return tile[2];
            case('3'):
                return tile[3];
            case('4'):
                return tile[4];
            case('5'):
                return tile[5];
            case('6'):
                return tile[6];
            case('7'):
                return tile[7];
            case('8'):
                return tile[8];
            case('9'):
                return tile[9];
            case('A'):
                return tile[36];
            case('B'):
                return tile[37];
            case('C'):
                return tile[38];
            default:
                int index = (code - 'a' + 10);
                return tile[index];
                
        }
            
    }
    
    
    
    
    public void draw(Graphics2D g2) {
        // parameters: image, x,y, length, width );
        
        
        
        // OKAY drawing the map is very complicated
        // essentially since it is a diamond layout, every other
        // tile has a different starting position, even and odd. hence the modulus flag
        
        int flag = 0;
        while(flag < verticalLimit) {
            int x = 0; 
            
            if(flag%2 == 0) {
                
                for(int i=0; i< tileWidth * horizontalLimit && x != horizontalLimit; i+=tileWidth) {
                    
                    int y = halfTileHeight *(flag);
                    g2.drawImage(getTileCode(mapArray[x][flag]).image,i,y,tileLength,tileWidth ,null);
                    x++;
                    
                }
            } 
            
            else {
                
                for(int i=-116; i < tileWidth * horizontalLimit && x != horizontalLimit; i+=tileWidth) {
                    
                    int y = halfTileHeight *(flag);
                    g2.drawImage(getTileCode(mapArray[x][flag]).image,i,y,tileLength,tileWidth ,null);
                    x++;
                } 
            }
        flag++;
        }
    }
    
    public void updateTile(int x, int y) {
        //Resolution of fullscreen is roughly 2194, 1234
        /*
        Logic: divide mouseX by 219.4 and divide mouseY by 123.4
        This will seperate the region into roughly determine the grid
        
        */
        
        for(int i=0; i<horizontalLimit;i++) {
            for(int j=0;j<verticalLimit;j++) {
                switch(mapArray[i][j]) {
                    case('1'):
                        mapArray[i][j] = '0';
                        break;
                    case('3'):
                        mapArray[i][j] = '2';
                        break;
                    case('5'):
                        mapArray[i][j] = '4';
                }
            }
        }
        
        double divX;
        double divY = Math.round(((double) y) / 77.125);
        
        if(divY%2 == 1) {
            
            divX = Math.round(((double) x) / 243.778);
        } else {
            x-=98;
            divX = Math.round(((double) x) / 230.947);
        }
        
        
        returnRow = (int) divX;
        returnCol = (int) divY;
        try {
            switch(mapArray[returnRow][returnCol]) {
                case('0'):
                    mapArray[returnRow][returnCol] = '1';
                    break;
                case('2'):
                    mapArray[returnRow][returnCol] = '3';
                    break;
                case('4'):
                    mapArray[returnRow][returnCol] = '5'; 
                    break;

            }
        } catch(ArrayIndexOutOfBoundsException e) {
        }
        
    }
    
    public int getCol() {
        return returnCol;
    }
    
    public int getRow() {
        return returnRow;
    }
    
}
