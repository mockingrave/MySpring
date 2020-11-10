package myspring.mvc.services;

import myspring.database.entities.Url;
import myspring.database.repositories.UrlRepository;
import myspring.mvc.DTO.UrlDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public long convertToShortUrl(UrlDTO request) {
        Url url = new Url();
        url.setLongUrl(request.getLongUrl());
        try {
            return urlRepository.getUrlByLongUrl(url.getLongUrl()).getId();
        }catch (Exception e){
            urlRepository.insert(url);
        }
        return urlRepository.getUrlByLongUrl(url.getLongUrl()).getId();
    }

    public String getOriginalUrl(String shortUrl) {
        long id = Long.parseLong(shortUrl);

        try {
            Url url = urlRepository.getUrlById(id);
            return url.getLongUrl();
        }catch (EntityNotFoundException e){
             return "There is no entity with " + shortUrl;
        }
    }
}
