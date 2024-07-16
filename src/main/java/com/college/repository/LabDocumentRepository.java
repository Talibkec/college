    package com.college.repository;

    import com.college.core.entity.Holiday;
    import com.college.core.entity.LabDocument;
    import org.springframework.data.repository.query.Param;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface LabDocumentRepository extends JpaRepository<LabDocument, Long> {

        @Query(value = "select * from lab_document order by id desc", nativeQuery = true)
        List<LabDocument> getAllDocuments();
        @Query(value = "select l from LabDocument l where l.depId = :deptId order by l.id desc")
        List<LabDocument> getLabByDeptId(@Param("deptId") Integer deptId);
    }
