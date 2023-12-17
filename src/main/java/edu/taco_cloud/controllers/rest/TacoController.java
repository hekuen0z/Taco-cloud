package edu.taco_cloud.controllers.rest;

import edu.taco_cloud.models.Taco;
import edu.taco_cloud.repositories.TacoRepository;
import edu.taco_cloud.services.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Сообщает, что все ответы контроллера должны включаться непосредственно
//в тело ответа, а не переноситься в модели в представления для отображения
@RestController
@RequestMapping(path = "/api/tacos",
produces = "application/json")
//Аннотация обработку межсайтовых запросов
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {

    private TacoService tacoService;

    @Autowired
    public TacoController(TacoService tacoService) {
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
}
