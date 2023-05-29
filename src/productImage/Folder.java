package productImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import base.BaseSetup;

public class Folder extends BaseSetup {

	public static void main(String[] args) {
		driverSetup();
		navigateUrl("https://www.amazon.com/");
		
		String searchTag = "Laptop Core i5 ";
		
		try {
			sendKeys(By.xpath("//input[@id='twotabsearchtextbox']"),searchTag + Keys.ENTER);
			String lastPageNum = " ";
			int count = 1;
			try {
				try {
					lastPageNum = getText(By.xpath("//div[@class='a-section a-text-center s-pagination-container']//span[@class='s-pagination-item s-pagination-disabled']"));
					try {
						int last = Integer.parseInt(lastPageNum);
						
						for(int i = 1; i<=last; i++) {
							try {
								Thread.sleep(3000);
								List<WebElement> listOfIteam = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
								int listOfIteamSize = listOfIteam.size(); 
								System.out.println("Size of list item: "+ listOfIteamSize);
								
								int index = 1;
								URL imageUrl = null;
								// image save on folder
								String folderPath = "G:/JavaSelenium/Amazon/AmazonImage/";
								// create the folder if it does not exist
								File folder = new File(folderPath);
								if (!folder.exists()) {
								    folder.mkdirs();
								}
								
								for (WebElement singleList : listOfIteam) {
									try {
										String url = driver.findElement(By.xpath("//div[@class='aok-relative']//img")).getAttribute("src");
										System.out.println("Image url["+ index +"]: "+ url);
										try {
								            // generate url
								            imageUrl = new URL(url);

								            // Read url
								            BufferedImage saveImages = ImageIO.read(imageUrl);

								            // Specify the file path where you want to save the image
								            String filePath = folderPath + "Laptop " + count++ + ".jpg";

								            // Save the image to the specified file location
								            ImageIO.write(saveImages, "jpg", new File(filePath));

								        } catch (IOException e) {
								            e.printStackTrace();
								        }

								        index++;
										} catch (Exception e) {
										System.out.println("Image url not founf..");
									}
								}
								try {
									click(By.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"));
									System.out.println("pagination clicked..");
								} catch (NoSuchElementException e) {
									System.out.println("There is no pages left..");
									break;
								}
								
							} catch (Exception e) {
								System.out.println("List of item not found..");
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				} catch (Exception e) {
					System.out.println("there is only one page..");
					try {
						Thread.sleep(3000);
						List<WebElement> listOfIteam = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));
						int listOfIteamSize = listOfIteam.size(); 
						System.out.println("Size of list item: "+ listOfIteamSize);
						
						int index = 1;
						URL imageUrl = null;
						
						for (WebElement singleList : listOfIteam) {
							try {
								String url = driver.findElement(By.xpath("//div[@class='aok-relative']//img")).getAttribute("src");
								System.out.println("Image url["+ index +"]: "+ url);
								try {
									// generate url
									imageUrl = new URL(url);
									
									//Read url
									BufferedImage saveImages = ImageIO.read(imageUrl);
									
									// Download images of current product
									ImageIO.write(saveImages, "jpg", new File("Laptop "+ count++ +".jpg"));
									
								} catch (IOException ex) {
									e.printStackTrace();
								}
								
								index++;
							} catch (Exception ex) {
								System.out.println("Image url not founf..");
							}
							
						}
						
					} catch (Exception ex) {
						System.out.println("List of item not found..");
					}
				}
				
			} catch (Exception e) {
				System.out.println("There are no image..");
			}
			
		} catch (Exception e) {
			System.out.println("Search bar dose not exist..");
		}
	}

}
