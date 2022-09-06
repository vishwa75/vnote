package com.appnotes.vnote.serviceimplementation;

import com.appnotes.vnote.DTO.ContentDTO;
import com.appnotes.vnote.DTO.VnotedataDTO;
import com.appnotes.vnote.model.Vnotedata;
import com.appnotes.vnote.repository.Vnoterepository;
import com.appnotes.vnote.service.Vnoteservice;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class Vnoteseviceimplementation implements Vnoteservice {
    @Autowired
    private Vnoterepository vnoterepository;

    @Override
    public List<Vnotedata> getnote() {
        return vnoterepository.findAll();
    }

    @Override
    public Vnotedata savenote(Vnotedata vnotedata) {

        return vnoterepository.save(vnotedata);

    }

    @Override
    public Vnotedata getnotebyid(long id) {
        if (vnoterepository.findById(id).isPresent()) {
            Vnotedata note = vnoterepository.findById(id).get();
            return note;
        } else {
            throw new RuntimeException(id + "not found");
        }
    }

    @Override
    public void deletenotebyid(long id) {
        if (vnoterepository.findById(id).isPresent()) {
            vnoterepository.deleteById(id);
        } else {
            throw new RuntimeException(id + "not found");
        }
    }

    @Override
    public Vnotedata updatenotebyid(VnotedataDTO vnotedataDTO, long id) {
        if (vnoterepository.findById(id).isPresent()) {
            Vnotedata note = vnoterepository.findById(id).get();
            note.setTopic(vnotedataDTO.getTopic());
            note.setGroup(vnotedataDTO.getGroup());
            note.setContent(vnotedataDTO.getContent());
            note.setFilename(vnotedataDTO.getFilename());
            note.setFormat(vnotedataDTO.getFormat());
            return vnoterepository.save(note);
        } else {
            throw new RuntimeException(id + "not found");
        }
    }

    @Override
    public Vnotedata editcontent(ContentDTO contentDTO, long id) {
        String content = "";
        if (vnoterepository.findById(id).isPresent()){
            Vnotedata note = vnoterepository.findById(id).get();
            content = note.getContent()+"\s"+contentDTO.getContent();
            note.setContent(content);
            return vnoterepository.save(note);
        }
        else {
            throw new RuntimeException(id + "not fount");
        }
    }

    @Override
    public List<Vnotedata> getbytopic(String topic, Writer writer) {
        List<Vnotedata> vnotes = vnoterepository.getbytopic(topic);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            for (Vnotedata vnote : vnotes) {
                csvPrinter.printRecord(vnote.getId(), vnote.getFormat(), vnote.getFilename(), vnote.getGroup(), vnote.getTopic());
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    

    @Override
    public List<Vnotedata> getbytopicres(String topic) {
        List<Vnotedata> vnotes = vnoterepository.getbytopicres(topic);
        return vnotes;
    }
}
