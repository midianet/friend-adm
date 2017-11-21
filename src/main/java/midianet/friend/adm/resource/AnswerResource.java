package midianet.friend.adm.resource;

import midianet.friend.adm.domain.Answer;
import midianet.friend.adm.domain.AnswerType;
import midianet.friend.adm.domain.User;
import midianet.friend.adm.repository.AnswerRepository;
import midianet.friend.adm.repository.AnswerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/answers")
public class AnswerResource {

    @Autowired
    private AnswerRepository repository;

    @GetMapping(path = "/paginate")
    public ResponseEntity<DataTableResponse> paginate(@RequestParam("draw")                      final Long    draw,
                                                      @RequestParam("start")                     final Long    start,
                                                      @RequestParam("length")                    final Integer length,
                                                      @RequestParam("search[value]")             final String  searchValue,
                                                      @RequestParam("columns[0][search][value]") final String  id,
                                                      @RequestParam("columns[1][search][value]") final String  description,
                                                      @RequestParam("columns[2][search][value]") final String  type,
                                                      @RequestParam("order[0][column]")          final Integer order,
                                                      @RequestParam("order[0][dir]")             final String  orderDir){
        final String[] columns          = new String[]{"id", "description","type"};
        final String[] orderColumns     = new String[]{"id", "description","type.method"};
        final List<Map<String, Object>> data = new ArrayList();
        final DataTableResponse dt = new DataTableResponse();
        final Long myId            = id.isEmpty()   ? null : Long.parseLong(id);
        final Long myTypeId        = type.isEmpty() ? null : Long.parseLong(type);
        dt.setDraw(draw);
        try {
            final Long qtTotal = repository.count();
            final Map<String, String> searchParams = new HashMap<>();
            if (!searchValue.isEmpty()) {
                searchParams.put(columns[1], searchValue);
            }
            final Integer page          = new Double(Math.ceil(start / length)).intValue();
            final PageRequest pr        = new PageRequest(page,length, new Sort(new Sort.Order(Sort.Direction.fromString(orderDir),orderColumns[order])));
            final Page<Answer> list = !id.isEmpty() || !description.isEmpty()  ||  !type.isEmpty() ? repository.findAll(Answer.filter(myId,description,myTypeId),pr) : repository.findAll(pr);
            final Long qtFilter         = list.getTotalElements();
            if (qtFilter > 0) {
                list.forEach(e  -> {
                    final HashMap<String,Object> l = new HashMap<>();
                    l.put("description",e.getDescription());
                    l.put("DT_RowId","row_" + e.getId());
                    l.put("type",e.getType().getMethod());
                    l.put("id",e.getId());
                    data.add(l);});
            }
            dt.setRecordsFiltered(qtFilter);
            dt.setData(data);
            dt.setRecordsTotal(qtTotal);
        } catch (Exception e) {
            //log.error(e);
            dt.setError("Datatable error "+ e.getMessage());
        }
        return new ResponseEntity(dt, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Answer> create(@RequestBody final Answer type){
        type.setId(null);
        final Answer n = repository.save(type);
        return new ResponseEntity<>(n,HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Answer> findById(@PathVariable final Long id){
        final Answer t = repository.findOne(id);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }


    @Transactional
    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> update(@PathVariable final Long id,  @RequestBody final Answer type){
        type.setId(id);
        repository.save(type);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void>delete(@PathVariable final Long id){
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
