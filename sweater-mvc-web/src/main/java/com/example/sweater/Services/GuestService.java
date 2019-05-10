package com.example.sweater.Services;
import com.example.sweater.Repositories.GuestRepository;
import com.example.sweater.domain.Event;
import com.example.sweater.domain.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("guestsService")
public class GuestService {
    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

   public int countOfquests(Event event)
    {
        Iterable<Guest> guests = guestRepository.findByevent(event);
        int count = ((List<Guest>) guests).size();
        return count;
    }

}
