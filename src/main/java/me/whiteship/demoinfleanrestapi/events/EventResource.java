package me.whiteship.demoinfleanrestapi.events;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class EventResource extends Resource<Event> {
    public EventResource(Event event, Link... links) {
        super(event, links);
        ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash(event.getId());
        add(selfLinkBuilder.withSelfRel());
        add(selfLinkBuilder.withRel("update-event"));
        add(linkTo(EventController.class).withRel("query-events"));
    }

    /*@JsonUnwrapped
    private Event event;

    public EventResource(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }*/
}
