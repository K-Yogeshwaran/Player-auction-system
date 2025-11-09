package com.playerAuction.player_auction_system.ControllerLayer;


import com.playerAuction.player_auction_system.Model.Player;
import com.playerAuction.player_auction_system.ServiceLayer.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    public String getCellValueAsString(Cell cell){
        if(cell == null){
            return "";
        }

        switch(cell.getCellType()){
            case STRING :
                return cell.getStringCellValue();

            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)){
                    return cell.getDateCellValue().toString();
                }
                else{
                    return String.valueOf(cell.getNumericCellValue());
                }

            case FORMULA:
                try{
                    return cell.getStringCellValue();
                }
                catch(IllegalStateException e){
                    return String.valueOf(cell.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case BLANK:
                return "";

            default :
                return "";

        }
    }

    public double parseBasePriceStr(String basePriceStr){

        if(basePriceStr == null || basePriceStr.isEmpty())
            return 0.0;

        basePriceStr = basePriceStr.trim().toUpperCase();

        if(basePriceStr.endsWith("C")){
            double basePrice = Double.parseDouble(basePriceStr.replace("C",""));
            return basePrice;
        }
        else if(basePriceStr.endsWith("L")){
            double basePrice = Double.parseDouble(basePriceStr.replace("L",""));
            basePrice = basePrice / 100.0;
            return basePrice;
        }
        else{
            return 0.0;
        }
    }


    @PostMapping("/upload")
    public String getDataset(@RequestParam("file") MultipartFile file){
        List<Player> playerList = new ArrayList<> ();
        if(file.isEmpty()){
            return "Empty file. Please upload a valid file";
        }

        try(Workbook workbook = WorkbookFactory.create(file.getInputStream())){

            Sheet sheet = workbook.getSheetAt(0);
            int index = 1;
            for(Row row : sheet){

                if(row.getRowNum() == 0){
                    continue;
                }

                boolean isEmpty = true;
                for(int c = 0; c < 4; c++){
                    if(getCellValueAsString(row.getCell(c)).length() > 0){
                        isEmpty = false;
                        break;
                    }
                }
                if(isEmpty)
                    continue;

                String playerName = getCellValueAsString(row.getCell(0));
                String playerCountry = getCellValueAsString(row.getCell(1));
                String playerRole = getCellValueAsString(row.getCell(2));
                String basePriceStr = getCellValueAsString(row.getCell(3));
                double basePrice = parseBasePriceStr(basePriceStr);

                System.out.println(index+ ". "+ playerName + " | "+ playerCountry + " | " + playerRole + " | "+ basePriceStr);
                index++;

                playerList.add(new Player(playerName,playerCountry,playerRole,basePrice,false));
            }

            playerService.addPlayerList(playerList);
        }
        catch(IOException e){
            e.printStackTrace();
            return "Error Reading Excel File.";
        }

        return "File Processed Successfully.";
    }

    @GetMapping("/")
    public List<Player> getAllPlayers(){
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id){
        return playerService.getPlayerById(id);
    }

    @PutMapping("/")
    public String updatePlayer(@RequestBody Player player){
         playerService.updatePlayer(player);
         return "Player updated successfully";
    }

}
