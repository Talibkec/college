package com.college.service;

import com.college.core.entity.LinkItem;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/** Menus and link lists (table {@code link_item}). */
@Service
public interface LinkService {

    /** Lists known to the edit screen: code → name. */
    Map<String, String> lists();

    /**
     * The visible entries of a list as a tree (top level with children), or null if the page should show its own
     * links: list empty, table missing, or the list is switched off (unless {@code preview}).
     */
    List<LinkItem> render(String listCode, boolean preview);

    /** False if the list is switched off (setting {@code links.<code>.enabled} = "no"). */
    boolean enabled(String listCode);

    /** All entries of a list (also hidden ones), parents followed by their children. */
    List<LinkItem> allItems(String listCode);

    LinkItem item(Long linkId);

    boolean canEdit(Authentication authentication, String listCode);

    /** Creates or updates an entry; returns the validation error or null. */
    String save(LinkItem item);

    /** Deletes an entry and its sub-entries. */
    void delete(Long linkId);

    /** Moves an entry one place up (-1) or down (+1) among its siblings. */
    void move(Long linkId, int direction);

    void setEnabled(String listCode, boolean enabled);

    /**
     * The visible main-menu entry with this URL as [top-level entry] or [top-level entry, sub-entry], or null. Used by
     * the new pages {@code /page/<name>}: the page exists while a menu entry links to it. Independent of the main
     * menu's switch.
     */
    List<LinkItem> menuPath(String url);
}
