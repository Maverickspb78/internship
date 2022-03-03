package org.example.todo.Utils;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ParserId {

    private final PrintError printError;

    public int parseId(String idString){
        int id;
        log.debug("parseId: {}", idString);
        try {
            id = Integer.parseInt(idString);
        } catch (NumberFormatException exception){
            log.error("Не распарсить id: {}", idString, exception);
            printError.printError();
            return 0;
        } return id;
    }

}
