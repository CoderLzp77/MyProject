package com.dao;

import com.pojo.Askforleave;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

@Repository
public interface AskforLeaveDao {
    /**
     * @param askforleave
     * @return 影响行数
     * 注解插入
     */
/*    @Insert(value = "   insert into askforleave (ApplyTime,StartTime,EndTime,Duration,Applicant,Category,Reason,State,ApprovalId)\n" +
            "        value (#{applyTime},#{startTime},#{endTime},#{duration},#{applicant},#{category},#{reason},#{state},#{approvalId})")*/
    int queryStaff(Askforleave askforleave);
}