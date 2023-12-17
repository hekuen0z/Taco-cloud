package edu.taco_cloud.controllers.rest;

import edu.taco_cloud.models.Taco;
import edu.taco_cloud.services.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//Сообщает, что все ответы контроллера должны включаться непосредственно
//в тело ответа, а не переноситься в модели в представления для отображения
@RestController
@RequestMapping(path = "/api/tacos",
produces = "application/json") //produces - обрабатывать только те запросы, которые содержать заголовок /application/json
//Аннотация обработку межсайтовых запросов
@CrossOrigin(origins = "http://tacocloud:8080")
public class RestTacoController {

    private TacoService tacoService;

    @Autowired
    public RestTacoController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    /**
     *
     * @return - возвращает данные о последних рецептах тако
     */
    @GetMapping(params = "recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());

        return tacoService.findAllByPage(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTaco = tacoService.findById(id);
        if(optionalTaco.isPresent()) {
            return new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /**
     * Метод принимает JSON и создает объект по полученным данным
     * @param taco - тело запроса преобразуется в объект Taco
     * @return - хранимый объект Taco
     */
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoService.save(taco);
    }



}
