package MemeSortScore;

import org.testng.Assert;
import org.testng.annotations.*;


public class NewTest {
  private Meme meme=new Meme();
  @BeforeTest
  public void beforeTest() 
  {
	  System.out.println("----Starting Test----");
  }
  @Test
  public void testMemeSort()
  {
	  meme.ReadMemes();
	  meme.SortMemes();
	  String[] a={"But That's None of My Business","Doge! Much Wow, Such Fun...","Good Guy Greg","Grumpy Cat","What Does the Fox Say?"};
	  Assert.assertEquals(meme.SortedMeme.keySet().toArray(),a,"Test Failed");
  }
  @AfterTest
  public void afterTest()
  {
	  System.out.println("----Test Ended----");
  }
 
}
