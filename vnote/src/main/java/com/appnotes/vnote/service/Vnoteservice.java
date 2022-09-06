package com.appnotes.vnote.service;

import com.appnotes.vnote.DTO.ContentDTO;
import com.appnotes.vnote.DTO.VnotedataDTO;
import com.appnotes.vnote.model.Vnotedata;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.util.List;
@Service
public interface Vnoteservice {

    List<Vnotedata> getnote();
    Vnotedata savenote (Vnotedata vnotedata);
//    Vnotedata savenote (Vnotedata vnotedata);
    Vnotedata getnotebyid (long id);

    List<Vnotedata> getbytopicres (String topic);
    
    void deletenotebyid (long id);
    Vnotedata updatenotebyid (VnotedataDTO vnotedataDTO, long id);

    List<Vnotedata> getbytopic (String topic, Writer writer);

    Vnotedata editcontent(ContentDTO contentDTO, long id);


}
