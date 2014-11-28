package gameModelTests;

import static org.junit.Assert.*;


import org.junit.*;

import bomberMan.gamePlay.Model.*;
public class CellTest {
     Cell cell1;
     Cell cell2;
     BomberMan myBomberman;
    @Before 
    public void setUp(){
    	myBomberman = new BomberMan(1, 1);
    	myBomberman.setBrickPass(true);
    	myBomberman.setBombPass(true);
    	cell1 = new Cell(40, 40,myBomberman );
    	cell2 = new Cell(new GameObject("object"));
    }
	@Test
	public void testCellConstructor3Args(){
	
		assertEquals(40, cell1.getX()*CONSTANTS.TILE_SIDE_SIZE);
		assertEquals(40, cell1.getY()*CONSTANTS.TILE_SIDE_SIZE);
		assertTrue(cell1.isEmpty());
		assertSame(cell1.getBomberMan(), myBomberman);
		
	}
	@Test
	public void testCellConstructor1Arg(){
		assertEquals(cell2.isEmpty(), false);		
	}
	@Test
	public void testInsert()
	{
	  GameObject gObject = new GameObject("PowerUp");
	  cell1.insert(gObject);
	  assertEquals(cell1.isEmpty(), false);
	  cell1.remove(gObject);
	}
	@Test	
	public void testRemove()
	{
		GameObject gObject = new GameObject("PowerUp");
		cell1.remove(gObject);
		assertEquals(cell1.isEmpty(), true);
		
	}
	@Test
	public void testGetObjects()
	{
		  GameObject gObject = new GameObject("PowerUp");
		  cell1.insert(gObject);
		  assertTrue(cell1.getObjects().size() > 0);
		  cell1.remove(gObject);
	}
	@Test
	public void testGetImage()
	{     GameObject gObject = new BomberMan(1,1);
	      cell1.insert(gObject);
		 assertNotNull(cell1.getImage());
		  cell1.remove(gObject);
	}
	@Test	
	public void testIsEmpty()
	{
		 assertTrue(cell1.isEmpty());
		
	}
	@Test
	public void testIsEmptyBrickException()
	{     GameObject gmObject = new GameObject("Brick");
	     GameObject gmObject1 = new GameObject("Concrete");
		 assertTrue(cell1.isEmptyBrickException());	
		 cell1.insert(gmObject);
		 assertTrue(cell1.isEmptyBrickException());
		 cell1.remove(gmObject);
		 cell1.insert(gmObject1);
		 assertEquals(cell1.isEmptyBrickException(), false);
		 cell1.remove(gmObject1);
	}
	@Test
	public void testIsThereAnEnemy()
	{
		 Enemy enemy = new Enemy(1,1, 100, "Balloon");
		 assertEquals(cell1.isThereAnEnemy(), false);
		 cell1.insert(enemy);
		 assertEquals(cell1.isThereAnEnemy(), true);
		 cell1.remove(enemy);
		 
	}
	@Test
	public void testDeleteEnemies()
	{
		Enemy enemy = new Enemy(1,1, 100, "Balloon");
		Enemy enemy2 = new Enemy(1,2, 2100, "Oneil");
		Enemy enemy3 = new Enemy(1,3, 200, "LOL");
		cell1.insert(enemy);
		cell1.insert(enemy2);
		cell1.insert(enemy3);
		cell1.deleteEnemies();
		assertTrue(cell1.getObjects().size() == 0);

	}
	@Test
	public void testGetEnemies()
	{
		Enemy enemy = new Enemy(1,1, 100, "Balloon");
		Enemy enemy2 = new Enemy(1,2, 2100, "Oneil");
		Enemy enemy3 = new Enemy(1,3, 200, "LOL");
		cell1.insert(enemy);
		cell1.insert(enemy2);
		cell1.insert(enemy3);
		assertTrue(cell1.getObjects().size() == 3);
		assertEquals(cell1.getEnemies().get(0).getType(), "Balloon");
		assertEquals(cell1.getEnemies().get(1).getType(), "Oneil");
		assertEquals(cell1.getEnemies().get(2).getType(), "LOL");
		cell1.deleteEnemies();
		assertTrue(cell1.getObjects().size() == 0);

	}
	@Test
	public void testIsEmptyPowerUpException()
	{
		 GameObject gmObject = new GameObject("Brick");
	         
	     GameObject gObject3 = new GameObject("ExitDoor");
	     GameObject gObject4 = new GameObject("PowerUp");
	     Enemy enemy = new Enemy(1,1, 100, "Balloon");
	     GameObject gmObject5 = new GameObject("Bomb");
	     cell1.insert(gmObject);
	     
	     assertTrue(cell1.isEmptyPowerUpException());
	     cell1.remove(gmObject);
	     cell1.insert(gObject4);
	     assertEquals(cell1.isEmptyPowerUpException(), true);
	     cell1.remove(gObject4);
	     cell1.insert(gObject3);
	     assertTrue(cell1.isEmptyPowerUpException());
	     cell1.remove(gObject3);
	     cell1.insert(enemy);
	     assertTrue(cell1.isEmptyPowerUpException());
	     cell1.remove(enemy);
	     cell1.insert(gmObject5);
	     cell1.setHasABomb(true);
	     assertTrue(cell1.isEmptyPowerUpException());
	     cell1.remove(gmObject5);
	     cell1.setHasABomb(false);
		
	}
	@Test
	public void testIsEmptyBombException(){
		 cell1.setHasABomb(true);
		 assertEquals(cell1.isEmptyBombException(), false);
	     cell1.setHasABomb(false);
	     assertTrue(cell1.isEmptyBombException());
			
	}
	
	@Test
	public void testSetHasABomb(){
		 cell1.setHasABomb(true);
		 assertTrue(cell1.getHasABomb());
		 cell1.setHasABomb(false);
		 assertTrue(cell1.getHasABomb()==false);
	}
	
	@Test
	public void  testSetHasADetonatorBomb(){
		 cell1.setHasADetonatorBomb(true);
		 assertTrue(cell1.getHasADetonateBomb());
		 cell1.setHasADetonatorBomb(false);
		 assertTrue(cell1.getHasADetonateBomb()==false);
	}
	@Test
	public  void testGetHasABomb(){
		cell1.setHasADetonatorBomb(true);
		assertTrue(cell1.getHasABomb()==false);
		cell1.setHasADetonatorBomb(false);
		assertTrue(cell1.getHasABomb()==false);
		cell1.setHasABomb(true);
		assertTrue(cell1.getHasABomb());
		cell1.setHasABomb(false);
		assertTrue(cell1.getHasABomb()==false);
		}
	@Test
	public void testGetHasADetonateBomb(){
		
		 cell1.setHasADetonatorBomb(true);
		 assertTrue(cell1.getHasADetonateBomb());
		 cell1.setHasADetonatorBomb(false);
		 assertTrue(cell1.getHasADetonateBomb()==false);
	}
	@Test
	public  void testSearchBomb(){
		 Bomb gmObject5 = new Bomb(1,1,1000,0, false, "Bomb");
	     cell1.insert(gmObject5);
	     assertSame(gmObject5, cell1.searchBomb());
	     cell1.remove(gmObject5);
	}
	@Test
	public void testDeleteElement(){
		
		GameObject gmObject6 = new GameObject("Brick");
	    
	    cell1.insert(gmObject6);
		
		cell1.deleteElement("Brick");
		assertTrue(cell1.getObjects().size()==0);
	}
	@Test
	public void testSetFlameImages(){
		
		cell1.setFlameImages();
		assertTrue(cell1.hasAFlame());
		cell1.removeFlames();
	}
	@Test
	public void testSearchFlameObject(){
		
		
		cell1.setFlameImages();
		assertTrue(cell1.searchFlameObject() == 0);
		cell1.removeFlames();
	}
	@Test
	public void testSearchTheCell()
		{
		 GameObject gmObject = new GameObject("Brick");
		 cell1.insert(gmObject);
		 assertTrue(cell1.searchTheCell("BRICK"));
		 cell1.remove(gmObject);
	     GameObject gObject3 = new GameObject("Concrete");
	     cell1.insert(gObject3);
	     assertTrue(cell1.searchTheCell("CONCRETE"));
	     cell1.remove(gObject3);
	     GameObject gObject4 = new GameObject("PowerUp");
	     cell1.insert(gObject4);
	     assertTrue(cell1.searchTheCell("POWERUP"));
	     cell1.remove(gObject4);
	     GameObject gObject5 = new GameObject("Bomb");
	     cell1.insert(gObject5);
	     cell1.setHasABomb(true);
	     assertTrue(cell1.searchTheCell("BOMB"));
	     cell1.setHasABomb(false);
	     cell1.remove(gObject5);
	     assertTrue(cell1.searchTheCell("BOMB") == false);
	     assertTrue(cell1.searchTheCell("BRICK") == false);
	     assertTrue(cell1.searchTheCell("CONCRETE") == false);
	     assertTrue(cell1.searchTheCell("POWERUP") == false);
	     assertTrue(cell1.searchTheCell("RANDOM") == false);
	}
	@Test
	public void testSearcHasAConcreteWall()
	{
		 GameObject gObject3 = new GameObject("Concrete");
		 cell1.insert(gObject3);
		 assertTrue(cell1.searcHasAConcreteWall());
		 cell1.remove(gObject3);
		 assertTrue(cell1.searcHasAConcreteWall() == false);
	}
	@Test
	public void testSearcHasABrickWall()
		{
		 GameObject gObject3 = new GameObject("Brick");
		 cell1.insert(gObject3);
		 assertTrue(cell1.searcHasABrickWall());
		 cell1.remove(gObject3);
		 assertTrue(cell1.searcHasABrickWall()==false);
		}
	@Test
	public void searcHasAPowerUp(){
		 GameObject gObject3 = new GameObject("PowerUp");
		 cell1.insert(gObject3);
		 assertTrue(cell1.searcHasAPowerUp());
		 cell1.remove(gObject3);
		 assertTrue(cell1.searcHasAPowerUp()==false);
		
				}
	@Test
    public void testSearchAPowerUp(){
		PowerUp gObject3 = new PowerUp();
		 cell1.insert(gObject3);
		 assertSame(gObject3, cell1.searchAPowerUp());
		 cell1.remove(gObject3);
					
				}
	@Test
	public void testRemoveFlames(){
		
		cell1.setFlameImages();
		cell1.removeFlames();
		assertTrue(cell1.getObjects().size()==0);
		
	}

	
	@Test
	public void testHasAFlame(){
		cell1.setFlameImages();
		assertTrue(cell1.hasAFlame());
		cell1.removeFlames();
		assertTrue(cell1.hasAFlame()== false);

	}
	
	@Test
	public void testRemovePowerUp(){
		PowerUp gObject3 = new PowerUp();
		cell1.insert(gObject3);
		assertTrue(cell1.searcHasAPowerUp());
		cell1.remove(gObject3);
		assertTrue(cell1.searcHasAPowerUp() == false);
			
	}
	@Test
	public void  testGetBomberMan(){
		
		assertSame(myBomberman, cell1.getBomberMan());
	}
	
	@Test
	public void testSetParent(){
		Cell test = new Cell(1,1, myBomberman);
		cell1.setParent(test);
		assertSame(cell1.getParent(), test);
		cell1.setParent(null);
	}
	@Test
	public void testGetParent(){
		assertSame(cell1.getParent(), null);
		Cell test = new Cell(1,1, myBomberman);
		cell1.setParent(test);
		assertSame(cell1.getParent(), test);
	}
	@Test
	public void testSetGscore() {
		
		assertEquals(0, cell1.getGScore());
		cell1.setGscore(100);
		assertEquals(100, cell1.getGScore());
		
	}
	@Test
	public void testGetGScore() {
		assertEquals(0, cell1.getGScore());
		
	}
	@Test
	public void testSetFscore() {
		assertEquals(0, cell1.getFscore());
		cell1.setFscore(100);
		assertEquals(100, cell1.getFscore());
	}
	@Test
	public void testGetFscore() {
		assertEquals(0, cell1.getFscore());
	}
	@Test
	public void testGetX(){
		assertEquals(40/CONSTANTS.TILE_SIDE_SIZE, cell1.getX());
	}
	@Test
	public void testGetY(){
		assertEquals(40/CONSTANTS.TILE_SIDE_SIZE, cell1.getY());
	}
	@Test
	public void testSearcHasAExit()
	{
		assertTrue(cell1.searcHasAExit() == false);
		ExitDoor1 gmObject = new ExitDoor1(1,1, CONSTANTS.EXIT_IMAGE,"ExitDoor");
		cell1.insert(gmObject);
		assertTrue(cell1.searcHasAExit());
		cell1.remove(gmObject);
	}
	

}
