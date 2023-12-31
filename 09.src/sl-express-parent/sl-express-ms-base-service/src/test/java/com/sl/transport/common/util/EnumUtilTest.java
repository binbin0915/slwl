package com.sl.transport.common.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.EnumUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * EnumUtil单元测试
 *
 * @author looly
 *
 */
@Slf4j
public class EnumUtilTest {

	/**
	 * 枚举类中所有枚举对象的name列表
	 */
	@Test
	public void getNamesTest() {
		List<String> names = EnumUtil.getNames(TestEnum.class);
		boolean equalList = CollUtil.isEqualList(CollUtil.newArrayList("TEST1", "TEST2", "TEST3"), names);
		log.info(equalList + "");
	}

	/**
	 * 获得枚举类中各枚举对象下指定字段的值
	 */
	@Test
	public void getFieldValuesTest() {
		List<Object> types = EnumUtil.getFieldValues(TestEnum.class, "type");
		Assert.assertEquals(CollUtil.newArrayList("type1", "type2", "type3"), types);
	}

	/**
	 * 获得枚举类中所有的字段名
	 */
	@Test
	public void getFieldNamesTest() {
		List<String> names = EnumUtil.getFieldNames(TestEnum.class);
		Assert.assertTrue(names.contains("type"));
		Assert.assertTrue(names.contains("name"));
	}

	@Test
	public void getByTest() {
		// 枚举中字段互相映射使用
		TestEnum testEnum = EnumUtil.getBy(TestEnum::ordinal, 1);
		Assert.assertEquals("TEST2", testEnum.name());
	}

	@Test
	public void getFieldByTest() {
		// 枚举中字段互相映射使用
		String type = EnumUtil.getFieldBy(TestEnum::getType, Enum::ordinal, 1);
		Assert.assertEquals("type2", type);

		int ordinal = EnumUtil.getFieldBy(TestEnum::ordinal, Enum::ordinal, 1);
		Assert.assertEquals(1, ordinal);
	}

	@Test
	public void likeValueOfTest() {
		TestEnum value = EnumUtil.likeValueOf(TestEnum.class, "type2");
		Assert.assertEquals(TestEnum.TEST2, value);
	}

	@Test
	public void getEnumMapTest() {
		Map<String,TestEnum> enumMap = EnumUtil.getEnumMap(TestEnum.class);
		Assert.assertEquals(TestEnum.TEST1, enumMap.get("TEST1"));
	}

	@Test
	public void getNameFieldMapTest() {
		Map<String, Object> enumMap = EnumUtil.getNameFieldMap(TestEnum.class, "type");
		assert enumMap != null;
		Assert.assertEquals("type1", enumMap.get("TEST1"));
	}

	public enum TestEnum{
		TEST1("type1"), TEST2("type2"), TEST3("type3");

		TestEnum(String type) {
			this.type = type;
		}

		private final String type;
		@SuppressWarnings("unused")
		private String name;

		public String getType() {
			return this.type;
		}

		public String getName() {
			return this.name;
		}
	}
}
